(ns deps-nvd.core
  (:require [clojure.edn :as edn]
            [clojure.data.json :as json]
            [clojure.tools.deps.alpha :as deps]
            [clojure.tools.deps.alpha.util.maven :as mvn]
            [nvd.core])
  (:import [java.io File]))

(def temp-file (java.io.File/createTempFile ".deps-nvd_" ".json"))

(defn get-jars [filename]
  (->> (-> filename
           slurp
           edn/read-string
           (update :mvn/repos merge mvn/standard-repos)
           (deps/resolve-deps {}))
       vals (mapcat :paths) distinct))

(defn -main [command & args]
  (let [deps-file    "deps.edn"
        config       (-> deps-file slurp edn/read-string :nvd)
        path         (.getAbsolutePath temp-file)
        project-name (-> (File. "deps.edn") .getAbsoluteFile .getParentFile .getName)
        opts         (merge
                      (select-keys config [:group :version :nvd])
                      {:name      project-name
                       :classpath (get-jars deps-file)
                       :cmd-args  args})]

    (spit path (json/write-str opts))

    (case command
      "check"  (nvd.task.check/-main path)
      "purge"  (nvd.task.purge-database/-main path)
      "update" (nvd.task.update-database/-main path))))
