/**
  * Created by jthgil on 11/30/16.
  */
object Executable {
  def main(args: Array[String]): Unit ={
    var exit = false
    while (!exit) {
      printf("Welcome to the Normal Reduction Calculator!\n")
      printf("(Lx.x) defines a term. Eg: (Lz.z)((Lx.x)(Ly.(Lz.z)y)).\n")
      printf("Input your terms here:\n")
      var input = scala.io.StdIn.readLine()
      printf(input)
      if (input == "exit") {
        exit = true
      }
      printf("Done! \n")
    }
  }

}