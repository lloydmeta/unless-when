# `unless` and `when` for Scala [![Build Status](https://travis-ci.org/lloydmeta/unless-when.svg?branch=master)](https://travis-ci.org/lloydmeta/unless-when)

`unless` and `when` macros for Scala 2.10+.

These are your run of the mill macros that you cut your teeth on when learning Lisp macros. Done mainly as an exercise
to learn Scala macros with quasiquotes.

In case it was non-obvious, since these are macros, the evaluation of the second argument is "lazy", meaning it
doesn't get evaluated if the predicate is does not satisfy unless/when semantics.

## Installing

Coming soon ... (if you saw this message then please go outside and have some fun)

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
