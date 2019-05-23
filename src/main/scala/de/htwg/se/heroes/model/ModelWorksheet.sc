var list = List(1,2)
4+3
var list2 = list


list match {
  case Nil => 2
  case head::stack => {
    list2 = list
  }
}

list
list2