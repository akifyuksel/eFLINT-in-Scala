object Types {
  type DomId = String
  type Tagged = (Elem, DomId)
  type Arguments = Either[List[Term], List[Modifier]]
  type Subs = List[(Var, Tagged)]
  type Initializer = List[Effect]
  type Scenario = List[Statement]
}