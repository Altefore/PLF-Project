/**
  * Created by jthgil on 11/30/16.
  * (lx.x)(ly.y)(lz.z)
  * (lx.x)(ly.(lz.z)y)(lx.x)(ly.(lz.z)y)
  *
  */

import scala.util.matching.Regex


object Executable {
 
  def main(args: Array[String]): Unit ={
    var exit = false
    while (!exit) {
      printf("Welcome to the Normal Reduction Calculator!\n")
      printf("(Lx.x) defines a term. Eg: (Lz.z)(Lx.x)(Ly.(Lz.z)y).\n")
      printf("Input your terms here:\n")
      var input = scala.io.StdIn.readLine()
      //printf(input + "\n")
      if (input == "exit") {
        exit = true
      }

      var values: Array[String] = new Array[String](20)
      var i = 0
      val count = 0
      //      values(count) = parse(input, i, count)
      values = parse(input, i, count)
      //parse(input, i, count)
      //for (var value <- 0 in count) {

      //BEGIN REDUCTION
      printf("\nReduction ("+i+"):\n")
      while (values(i) != null) {
       printf(values(i))
        i += 1
      }
      i = 0
      var j = i
      var reduction = 1
      while (values(i) != null) {
        j = i
        values(i) = reduceInput(values, i)
        if(values(i) != null) {
          if (parseCount(values(i), i) < 2) {
            i += 1
            j += 1
          }
          else{
            values(i) = extractValue(values(i))

          }
        }
        if(values(j) != null)
          printf("\nReduction ("+reduction+"):\n")
          reduction += 1
        while(values(j) != null) {
          printf(values(j))
          j += 1
        }

      }
      printf("\n")
    }
  }

  //calls checkFormat, extractValue and parseCount
  def reduceInput(values: Array[String], index: Int): String ={
    if(checkFormat(values(index)) && parseCount(values(index), index) == 1) {
      val extract = extractValue(values(index))
      values(index) = extract
     // printf("Extract = " + extract + "\n")
      return extract
    }
    else {
      return values(index)
    }
  }

  //returns true if theres a normal form that exists in the input
  def checkFormat(input : String): Boolean ={
    val pattern = new Regex("[Ll][a-zA-Z](.)[a-zA-Z]")
    val str = pattern findFirstIn input
    //val text = str.asInstanceOf[String]
    if (str isDefined) {
      //println(str)
      return true
    }
    else{
      println("Does not fit format!")
      return false
    }


  }

  //if there is a nested value in this value, it will be extracted
  //ex: (ly.(lx.x)y) => (lx.x)
  def extractValue(input: String): String ={
    var i = 0
    //var pCounter = 0
    //var vCounter = count
    var hold = 0
    var values = new Array[String](10)

    while ( i < input.length() ){

      if (input(i) == '(' ){
        //pCounter +=1
//        if (pCounter == 1)
          hold = i

      }
      if (input(i) == ')') {
        values(1) = input.substring(hold, i + 1)
        return values(1)
        //hold = i+1

      }
      i += 1
    }
    return null
  }

  //this parses the user input and stores all values in string array called 'values'
  def parse(input: String, index : Int, count : Int ): Array[String] ={
    var i = index
    var pCounter = 0
    var vCounter = 0
    var hold = index
    var values = new Array[String](10)

    while ( i < input.length() ){

      if (input(i) == ')'){
        pCounter -= 1
      }
      if (input(i) == '(' ){
        pCounter +=1
        if (pCounter == 1)
          hold = i

      }
      if (input(i) == ')' && pCounter == 0) {
        values(vCounter) = input.substring(hold, i + 1)
        vCounter += 1
        //hold = i+1

      }
      i += 1
    }
    values
  }
  //checks to see how many values exist in the string and returns the count
  def parseCount(input: String, index : Int): Int ={
    var i = index
    var pCounter = 0
    var vCounter = 0
    var hold = index
    var values = new Array[String](10)

    while ( i < input.length() ){


      if (input(i) == ')'){
        //values(vCounter) = input.substring(hold, i + 1)
        vCounter += 1
        //hold = i+1

      }
      i += 1
    }
    vCounter
  }

}
