# deps-nvd

This small utility allows you to check the dependencies of
tools-deps-based Clojure projects for vulnerabilities by cheking the
[National Vulnerability Database](https://nvd.nist.gov/). This is
achieved by wrapping
[nvd-clojure](https://github.com/rm-hull/lein-nvd) which is a
Leiningen plugin.

## Usage

Add this alias to your `deps.edn` file:

```clojure
:nvd {:extra-deps {deps-nvd {:git/url "git@github.com:BareSquare/deps-nvd.git"
                             :sha     "25fdfe76cd971c42d85539edb703e9f41661dfdd"}} ;;or replace this with the latest commit sha
      :main-opts  ["-m" "deps-nvd.core" "check"]}
```

Then from the command line:

```
clojure -A:nvd
```

The first time you run it (and every time an update is necessary) this
will download data files from the NVD and then will produce a report
of vulnerabilities for your project. See
[lein-nvd](https://github.com/rm-hull/lein-nvd) for more details on
other tasks such as `purge` and `update` and their parameters.

## Limitations

Only JAR dependencies are checked, it is not possible to check git
dependencies.

## License

The MIT License (MIT)

Copyright (c) 2019 baresquare

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
