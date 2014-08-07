# `unless` and `when` for Scala [![Build Status](https://travis-ci.org/lloydmeta/unless-when.svg?branch=master)](https://travis-ci.org/lloydmeta/unless-when)

`unless` and `when` macros for Scala 2.10+.

These are your run of the mill macros that you cut your teeth on when learning Lisp macros. Done mainly as an exercise
to learn Scala macros with quasiquotes.

In case it was non-obvious, since these are macros, the evaluation of the second argument (or the first in the trailing
variations) is "lazy", meaning it doesn't get evaluated if the predicate does not satisfy unless/when semantics.

## Installing

Add the following to your `build.sbt`

```scala
libraryDependencies += "com.beachape.extensions" %% "unless-when" % "0.0.2"
```

If the above does not work because it cannot be resolved, its likely because it hasn't been synced to Maven central yet.
In that case, download a SNAPSHOT release of the same version by adding this to `build.sbt`

```scala
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.beachape.extensions" %% "unless-when" % "0.0.2-SNAPSHOT"
```

## Example

```scala
import scala.ext.UnlessWhen._

println(when(4 < 2)(0))
// #=> None

println(when(4 > 2)(0))
// #=> Some(0)

println(when(4 < 2){ Iterator.from(0).sum })
// #=> None

println(unless(4 < 2)(0))
// #=> Some(0)

println(unless(4 > 2)(0))
// #=> None

println(unless(4 > 2){ Iterator.from(0).sum })
// #=> None

// Trailing unless and whens are also available and lazy

println(Iterator.from(0).sum when 4 < 2)
// #=> None

println(Iterator.from(0).sum unless 4 > 2)
// #=> None
```
