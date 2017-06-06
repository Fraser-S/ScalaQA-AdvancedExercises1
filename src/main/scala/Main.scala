/**
  * Created by Administrator on 06/06/2017.
  */

import java.io.FileNotFoundException

import scala.io.Source

object Main {

  def readFromFile(filename : String)/*: Array[String]*/: Unit = {
    //reads the data in from a file, catches exceptions if something goes wrong
    try {
      //gets the file source, used to close the file
      val bufferedSource = Source.fromFile(filename)

      //read the file
      for (line <- bufferedSource.getLines) {
        println(line)
      }

      //close the file
      bufferedSource.close
      }
    catch {
      case ex: FileNotFoundException => println("Error! File not found.")
      case ex: IndexOutOfBoundsException => println("Error! index out of range")
      case _ => println("Error! unexpected Exception")
    }
  }

  def main(args:Array[String]) : Unit ={

    //new java.io.File(dirName).listFiles.filter(_.getName.endsWith(".txt"))
    readFromFile("test.txt")
  }
}
