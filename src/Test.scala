//test: Test

import Interp.interp
import Explain.explain
import org.scalatest.FunSuite

class Test extends FunSuite {

  test("(5 > 3 or (3 <= 10) or (5 < 3)) and (not(1!=1) or not(5 == 1)) is true") {
    assertResult(ResBool(true)) {
      interp(Or(G(IntLit(5), IntLit(3)),And(Or(Leq(IntLit(3), IntLit(10)), L(IntLit(5), IntLit(3))), Or(Not(Eq(IntLit(1), IntLit(1)))
        , Not(Neq(IntLit(5), IntLit(1)))))))._1
    }
  }
  test("(18 - 4) >= (10 / 2) + ((12 * 2) mod 5) = 9") {
    assertResult(ResBool(true)) {
      interp(Geq(Sub(IntLit(18), IntLit(4)), Add(Div(IntLit(10), IntLit(2)), Mod(Mult(IntLit(12)
        , IntLit(2)), IntLit(12)))))._1
    }
  }

}
