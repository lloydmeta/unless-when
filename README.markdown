# Unless-When extensions [![Build Status](https://travis-ci.org/lloydmeta/unless-when.svg?branch=master)](https://travis-ci.org/lloydmeta/unless-when)

`unless` and `when` macros for Scala 2.10+. 

These are your run of the mill macros that you cut your teeth on when learning Lisp macros. Done mainly as an exercise 
to learn Scala macros with quasiquotes.

## Installing

Coming soon ... (if you saw this message then please go outside and have some fun)

## Example

```scala
import scala.ext.UnlessWhen._

println(when(4 < 2)(0))
// #=> None

println(when(4 > 2)(0))
// #=> Some(0)

println(unless(4 < 2)(0))
// #=> Some(0)

println(unless(4 > 2)(0))
// #=> None
```