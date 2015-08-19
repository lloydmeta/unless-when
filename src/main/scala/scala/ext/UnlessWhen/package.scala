package scala.ext

import scala.language.implicitConversions
import scala.language.experimental.macros
/**
 * Created by Lloyd on 8/7/14.
 */

/**
 * Import this package to get unless and when support in your code
 *
 * {{{
 * import scala.ext.unlesswhen._
 * }}}
 */
package object unlesswhen {

  /**
   * Runs the function if the given predicate evaluates to true. Returns None if the predicate returns
   * false or the result of the function wrapped in a Some if it returns true.
   *
   * This is a macro expansion, so the function provided is evaluated "called lazily"
   *
   * @param p precedent to test with
   * @param f function to compute
   */
  def when[A](p: Boolean)(f: => A): Option[A] = {
    if(p) Option(f) else None
  }

  /**
   * Runs the function if the given predicate evaluates to false. Returns None if the predicate returns
   * false or the result of the function wrapped in a Some if it returns true.
   *
   * This is a macro expansion, so the function provided is evaluated "called lazily"
   *
   * @param p precedent to test with
   * @param f function to compute
   */
  def unless[A](p: Boolean)(f: => A): Option[A] = {
    if(!p) Option(f) else None
  }

  /**
   * Implicit conversion to a TrailingWhen[A] handled by a macro
   *
   * Note that since the conversion is delegated to a macro, the computation of the result is
   * lazy (and won't happen unless the predicate satisfies when semantics)
   */
  implicit def toTrailingWhen[A](f: => A): TrailingWhen[A] = new TrailingWhen[A] {
    def when(p: Boolean): Option[A] = {
      if(p) Option(f) else None
    }
  }

  /**
   * Implicit conversion to a TrailingUnless[A] handled by a macro
   *
   * Note that since the conversion is delegated to a macro, the computation of the result is
   * lazy (and won't happen unless the predicate satisfies unless semantics)
   */
  implicit def toTrailingUnless[A](f: => A): TrailingUnless[A] = new TrailingUnless[A] {
    def unless(p: Boolean): Option[A] = {
      if(!p) Option(f) else None
    }
  }
}
