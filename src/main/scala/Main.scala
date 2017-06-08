/**
  * Created by Fraser on 06/06/2017.
  **/

import java.io.FileNotFoundException
import java.util.HashMap

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.collection.JavaConversions._

object Main {

  def readFromFile(filename : String): Array[String] = {
    //declare the array to pass back
    var wordArray : Array[String] = Array()

    //try to read the data in from a file, catches exceptions if something goes wrong
    try {
      //loads the file contents as an array
      wordArray = Source.fromFile(filename).getLines().toArray
      }
    catch {
      //if there is an exception handle it
      case ex: FileNotFoundException => println("Error! File not found.")//file not found
      case ex: IndexOutOfBoundsException => println("Error! index out of range")//out of bounds
      case _ => println("Error! unexpected Exception")//any other error
    }

    //returns the array
    wordArray
  }

  def sortCharactersAlphabetically(word: String): String ={
    var noSwaps : Boolean = true
    var charArray : Array[Char] = word.toCharArray()
    //perform a sort
    do{
      //set no swaps to true
      noSwaps = true
      //Array.length returns the number of items in an array
      //for example abc would return 3. array equivalent is 0,1,2
      //due to need of stopping at the second last element need to take 2 away e.g 0,1
      for(i <- 0 to charArray.length-2) {
        //if current element is larger than
        if(charArray(i)>charArray(i+1)){
          //store current element
          var temp = charArray(i)
          //make next element current element
          charArray(i) = charArray(i+1)
          //add temp element to next element
          charArray(i+1) = temp
          //set no swaps to true
          noSwaps = false
        }
      }
    } while(!noSwaps)//only exit when no swaps occured

    //return the charArray as a string
    charArray.deep.mkString("")
  }

  def sortStrings(words : Array[String]): Array[String] ={
    var sortedWords = new Array[String](words.length)
    for(i <- 0 to words.length-1) {
      sortedWords(i) = sortCharactersAlphabetically(words(i))
    }
    sortedWords
  }

  def createHashMap(keys : Array[String], values: Array[String]): HashMap[String,ListBuffer[String]] ={
    //set up the hash map
    var wordHashMap : HashMap[String, ListBuffer[String]] = new HashMap
    //loop for each word
    for(i <- 0 to keys.length-1) {
      //if key does'nt exist create it
      if(!wordHashMap.containsKey(keys(i))){
        //add the key to the map
        var list:ListBuffer[String] = ListBuffer(values(i))
        wordHashMap.put(keys(i),list)//add it to the list
      } else {
        //get the list
        var list : ListBuffer[String] = wordHashMap.get(keys(i))
        list += values(i)//this updates the list
      }
    }
    wordHashMap
  }

  def displayWordWithLongestAnagram(hashMap : HashMap[String, ListBuffer[String]]): Unit ={
    //stores the highest anagram count and the words with the same count
    var highestAnagramCount : Int = 0
    var wordList : ListBuffer[String] = ListBuffer()

    //iterate through the hash map, need to have the
    for ((k,v) <- hashMap){
      v match{
        //if current word has more anagrams set this as the new word and size
        case a if v.size > highestAnagramCount => highestAnagramCount = v.size; wordList = ListBuffer(k)
        //if current word matches the current highest add it to the list
        case b if v.size == highestAnagramCount => wordList += k
        case _  => //do nothing
      }
    }

    //display the results depending on how many words had the largest amount
    wordList.size match{
      case 0 => println("No words in list")
      case 1 => println(wordList.mkString("") + ": has the most anagrams of " + highestAnagramCount)
      case _ => println(wordList.size + " words have the highest amount of anagrams of: " + highestAnagramCount + "\nwords with this amount of anagrams are:\n" + wordList.mkString(",\n"))
    }

  }

  def main(args:Array[String]) : Unit ={

    //new java.io.File(dirName).listFiles.filter(_.getName.endsWith(".txt"))
    var words = readFromFile("C:\\Users\\Administrator\\IdeaProjects\\ScalaQA-AdvancedExercises1\\src\\main\\scala\\test.txt")

    var sortedWords = sortStrings(words)

    var hashMap = createHashMap(sortedWords, words)

    //display the word with the most anagrams
    displayWordWithLongestAnagram(hashMap)
  }
}
