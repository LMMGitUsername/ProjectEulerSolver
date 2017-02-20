/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteulersolver;
import java.util.ArrayList;
import java.math.BigInteger;
/**
 *
 * @author Despreciada
 */
public class ProjectEulerSolver {

    /**
     * @param args the command line arguments
     */

    public static int sumOfMultiplesOfNumBelowSomeOtherNum(int n1, int n2, int limit)
    {
        int findMultiplesBelowThisNum = limit;
        
        int sumOfMultiplesOfNumUpToLimit = 0;
        limit--;
        while(limit > 0)
        {
            if(limit % n1 == 0 || limit % n2 == 0)   
            {
                sumOfMultiplesOfNumUpToLimit += limit;   
            }
            limit--;
        }
        return sumOfMultiplesOfNumUpToLimit;
    }
    
    public static boolean isEven(int n)
    {
        boolean isEven;
        if(n % 2 == 0)
        {
            isEven = true;
        }
        else
        {
            isEven = false;
        }
        return isEven;
    }
    
    public static int fibonacciSequenceLessThanSomeNumber(int limit)
    {
        int secondLast = 1;
        int last = 2;
        
        int sumOfEvenFibonacciNumbers = 0;
        int tempSecondLast;
        
        int counter = 1;
        
        //while(secondLast <= 4000000 && last <= 4000000)
        //while(newLast < limit && newSecondLast < limit)
        while(last < limit)
        {
            /*System.out.println("fib num " + counter + ": " + secondLast);
            counter++;
            System.out.println("fib num " + counter + ": " + last);
            counter++;*/
            //check for even numbers in both last and second last before getting new fib numbers
            if(isEven(last))
            {
                sumOfEvenFibonacciNumbers+=last;
            }
            /*if(isEven(secondLast))
            {
                sumOfEvenFibonacciNumbers+=secondLast;
            }*/
            //get next fibonacci numbers in the sequence and update last and second last
            tempSecondLast = secondLast;
            secondLast = last; //updated secondLast
            last += tempSecondLast; //updated last
        }
        return sumOfEvenFibonacciNumbers;
    }
    
    public static BigInteger BigIntegerSquareRoot(BigInteger bigInt)
    {
        BigInteger two = new BigInteger("2");
        BigInteger i = two;
        BigInteger result = i;
        //System.out.println("bigInt equals " + bigInt.toString());
        //first, let's just see what we get when we look at "square roots"
        //System.out.println(i.toString() + " squared is " + (i.multiply(i)).toString());
        while((i.multiply(i)).compareTo(bigInt) < 1) //while i squared is less than bigInt, keep going
        {
            //result = i.multiply(i);
            //System.out.println("is " + i.toString() + " squared less than " + bigInt.toString() + "?");
            //System.out.println("i squared equals " + i.multiply(i));
            if((i.multiply(i)).compareTo(bigInt) == 0)//my assumption is that there will be data loss, meaning that I don't have to worry about a decimal in the way.
            {
                //System.out.println("i = " + i.toString());
                return i;
            }
            //i = i.add(BigInteger.ONE);
            result = i;
            i = i.add(BigInteger.ONE);
            //System.out.println("i + 1 = " + i.toString());
        }
        //System.out.println("i + 1 = " + i.toString());
        return result;
    }
    
    public static boolean isPrime(String n)
    {
        //if(n == 0)
        BigInteger num = new BigInteger(n);
        BigInteger two = new BigInteger("2");
        //debugging :(
        System.out.println("num sent to isPrime is " + num);
        System.out.println(num.compareTo(two));
        if(num.compareTo(two) == -1)//if (n < 2) 
        {
            //System.out.println(n + " is less than 2! Exception being thrown!");
            //commenting out exception below to fix bug :( 10/8/2016
            throw new IllegalArgumentException("Number " + n + " must be a positive integer that is greater than or equal to 2.\n The definition of a prime number is that it is any positive integer greater than one whose only positive integer divisors are 1 and itself. I found this definition in Allan M. Kirch's \"Elementary Number Theory: A Computer Approach\"");
        }
        else
        {
            //for(int i = 2; i <= (int)Math.floor(Math.sqrt(n)); i++)
            BigInteger lookForFactorsUpToHere = BigIntegerSquareRoot(new BigInteger(n));
            //BigInteger one = new BigInteger("1"); 
            
            for(BigInteger i = two; i.compareTo(lookForFactorsUpToHere) != 1; i = i.add(BigInteger.ONE))
            {
                //System.out.println(n + " is greater than 2 so we are looking for more factors.");
                //System.out.println(num.toString() + " is greater than " + two.toString() + " so we are looking for more factors.");
                //if(n % i == 0)//if n % i == 0 then this number is composite and not prime
                if(num.mod(i).compareTo(BigInteger.ZERO) == 0)
                {
                    //System.out.println(i.toString() + " is a factor of " + num.toString() + "! " + num.toString() + " is not prime!");
                    return false;
                }
                //System.out.println("going through the for loop in isPrime() again to look for more factors.");
            }
            return true;
        }
    }
    
    //public static int findLargestPrimeFactorOf(int n)
    public static BigInteger findLargestPrimeFactorOf(String n)
    {
        BigInteger num = new BigInteger(n);
        //int result = 2;
        BigInteger two = new BigInteger("2");
        BigInteger result = two;
        
        BigInteger lookForFactorsUpToHere = BigIntegerSquareRoot(new BigInteger(n));
        
        //for(int i = (int)Math.floor(Math.sqrt(n)); i > 2; i--)
        for(BigInteger i = lookForFactorsUpToHere; i.compareTo(two) == 1; i = i.subtract(BigInteger.ONE))
        {
            //System.out.println(n + " is greater than 2 so we are looking for more factors.");
            //if(n % i == 0 && isPrime(i))//if n % i == 0 then this number is composite and not prime
            if(num.mod(i).compareTo(BigInteger.ZERO) == 0 && isPrime(i.toString()))
            {
                //System.out.println(i.toString() + " is a factor of " + num.toString() + "! " + num.toString() + " is not prime!");
                return i;
            }
            //System.out.println("going through the for loop in findLargestPrimeFactorOf() again to look for more factors.");
        }
        return result;
    }
    
    public static ArrayList generateNdigitProducts(int n)
    {
        ArrayList numsToBeMultiplied = new ArrayList();
        //n tells us how we calculate our products.
        //String numToString = Integer.toString(threeDigs);
        //System.out.print("length of " + threeDigs + " is " + numToString.length());
        int numsToBeMultiplyin = 1;
        
        while(Integer.toString(numsToBeMultiplyin).length() <= n)
        {
            if(Integer.toString(numsToBeMultiplyin).length() == n)
            {
                numsToBeMultiplied.add(numsToBeMultiplyin);
                //System.out.println("numsToBeMultiplyin = " + numsToBeMultiplyin);
            }
            numsToBeMultiplyin++;
        }
        //now, get the multiples
        ArrayList result = new ArrayList();
        for(int i = 0; i < numsToBeMultiplied.size(); i++)
        {
            //multiply each number by every other number in array, add product to new arrayList result
            for(int j = 0; j < numsToBeMultiplied.size(); j++)
            {
                int addMeToResult = (int)numsToBeMultiplied.get(i)*(int)numsToBeMultiplied.get(j);
                if(result.contains(addMeToResult) == false)
                {
                   result.add(addMeToResult);
                    //System.out.println("number being added to array list of products = " + (int)numsToBeMultiplied.get(i)*(int)numsToBeMultiplied.get(j));
                }
            }
        }
        return result;
    }
    
    public static ArrayList arrayListInsertionSort(ArrayList al)
    {
        //for i ← 1 to length(A)-1
        int j;
        for(int i = 1; i < al.size(); i++)
        {
    //j ← i
            j = i;
    //while j > 0 and A[j-1] > A[j]
            while(j > 0 && (int)al.get(j - 1) > (int)al.get(j))
            {
      //  swap A[j] and A[j-1]
                int temp = (int)al.get(j);
        //j ← j - 1
                al.set(j, al.get(j - 1));
                al.set(j - 1, temp);
                j--;
            }
    //end while
//end for*/
        }
        return al;
    }
    
    public static boolean isPalindromicNumber(int n)
    {
        String numToString = Integer.toString(n);
        //we don't care about the middle digit in case the string has odd length
        int lengthOfString = numToString.length();
        for(int i = 0; i <= lengthOfString/2; i++)
        {
            if(numToString.charAt(i) != numToString.charAt(lengthOfString - i - 1))
            {
                return false;
            }
        }
        return true;
    }
    
/*Smallest multiple
Problem 5
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
*/
    public static ArrayList factorsOf(int n)
    {
        double ndub = (double)n;
        ArrayList result = new ArrayList();
        //debugging :(
        System.out.println("n given to factorsOf is " + n);
        for(double i = Math.sqrt(ndub); i > 0; i--)
        {
            if(ndub%i == 0 && isPrime(Integer.toString((int)i)))
            {
                result.add(i);
            }
        }
        //added on 10/8 to fix problem of certain small numbers having no factor results (2 and 3, I guess)
        if(result.isEmpty())
        {
            result.add(n);
        }
               //debugging :(
        System.out.println("factorsOf(" + n + ") are " + result.toString());
        return result;
    }
    
    public static int smallestMultiple(ArrayList listOfInts)
    {
        //debugging :(
        System.out.println(listOfInts.toString());
        /*we need the least common multiple of all of the numbers in listOfInts.
        to do this, we have to...(http://www.math.com/school/subject1/lessons/S1U3L3GL.html)*/
        ArrayList arrayListOfArrayList = new ArrayList();
        /*list prime factors of each number*/
        for(int i = 0; i < listOfInts.size(); i++)
        {
            int currentInt = (int)listOfInts.get(i);
            //debugging :(
            System.out.println("currentInt = " + currentInt);
            ArrayList currentIntsFactors = factorsOf(currentInt);
            //remove non-primes now
            for(int j = 0; j < currentIntsFactors.size(); j++)
            {
                int currentFactor = (int)currentIntsFactors.get(j);
                if(!isPrime(Integer.toString(currentFactor)))
                {
                    currentIntsFactors.remove(j);
                }
            }
            /*we should be left with just the prime factors, now. we need to be clever about how
            we do this now, because I'd rather not deal with an array of arrahs. I kinda want to 
            keep this as minimal as possible. Maybe we can start a master list of factors if
            this is the first int we're dealing with, then, if the next int doesn't have one of the factrs
            that is in the master list then we just have to remove that one, too.*/
            /*bluh, but, today, maybe to just make sure that I get through this quickly,
            I'll do the arraylist of arraylists >.<*/
            arrayListOfArrayList.add(currentIntsFactors);
            
        }
        /*Then multiply each factor the greatest number of times it occurs in either number. 
        If the same factor occurs more than once in both numbers, you multiply the factor 
        the greatest number of times it occurs.*/
        System.out.println(arrayListOfArrayList.toString());
        return -1; //dummy while I test
    }
      
    public static void main(String[] args) {
        // TODO code application logic here
        //problem 1: Multiples of 3 and 5
        /*int answerToTestPuzzleOne = sumOfMultiplesOfNumBelowSomeOtherNum(3, 5, 10);
        int answerToPuzzleOne = sumOfMultiplesOfNumBelowSomeOtherNum(3, 5, 1000);
        System.out.println("The answer to puzzle 1 test is " + answerToTestPuzzleOne);
        System.out.println("The answer to puzzle 1 is " + answerToPuzzleOne);*/
        
        //problem 2: Even Fibonacci Numbers
        //System.out.println("The answer to puzzle 2 test is " + fibonacciSequenceLessThanSomeNumber(4000000));
        
        //problem 3: Largest Prime Factor
        //System.out.println("The answer to puzzle 3 test is " + findHighestPrimeFactorOf(13195));
        //test for problem 3 solution helper function isPrime:
        /*for(int j = 2; j < 1001; j++)
        {
            if(isPrime(Integer.toString(j)))
            {
                System.out.println(j + " is prime.");
            }
            else
            {
                //System.out.println(j + " is composite, not prime.");
            }
        }
        */
        //tests for problem 3 
        //test BigIntegerSquareRoot
        //System.out.println("the square root of 9 is " + BigIntegerSquareRoot(new BigInteger("9")));    
        //BigIntegerSquareRoot(new BigInteger("9"));
        //System.out.println("Is 9 prime? " + isPrime("9"));
        //System.out.println(Integer.parseInt("29"));
        //System.out.println("Is 29 prime? " + isPrime("29"));
        //tests for problem 3 final solution findLargestPrimeFactorInArrayListOfFactors
        //int toFindFactorsWith = 13195;
        //String number = "13195";
        ////////String number = "600851475143";
        //System.out.println("The largest prime factor of 13195 is " + findLargestPrimeFactorOf(toFindFactorsWith));
        ////////System.out.println("The largest prime factor of " + number + " is " + findLargestPrimeFactorOf(number));
        
        /*int numberOfDigits = 1;
        int numDigits2 = 2;
        int numDigits3 = 3;
        
        int singleDig = 9;
        int twoDigNumb = 21;
        int threeDigs = 786;
        
        String numToString = Integer.toString(threeDigs);
        System.out.print("length of " + threeDigs + " is " + numToString.length());*/
        //test for array list insertion sort        
        /*ArrayList newArrayList = new ArrayList();
        newArrayList.add(5);
        newArrayList.add(5);
        newArrayList.add(9);
        newArrayList.add(2);
        newArrayList.add(77);
        newArrayList.add(7);
        
        newArrayList = arrayListInsertionSort(newArrayList);
        for(int m = 0; m < newArrayList.size(); m++)
        {
            System.out.println(newArrayList.get(m));
        }*/
        //test for generateNdigitProducts(int n)
        /*ArrayList listO1DigitProducts = arrayListInsertionSort(generateNdigitProducts(2));
        for(int m = 0; m < listO1DigitProducts.size(); m++)
        {
            System.out.println(listO1DigitProducts.get(m));
        }*/
        
        /*two three digit numbers; this is for problem 4.*/
       /* ArrayList one = new ArrayList();
        for(int i = 999; i > 99; i--)
        {
            for(int j = 999; j > 99; j--)
            {
                if(isPalindromicNumber(i*j))
                {
                    one.add(i*j);
                }
            }
        }
        ArrayList sorted = arrayListInsertionSort(one);
        System.out.println(sorted.get(sorted.size()-1));
        System.out.println(sorted.get(0));*/
       //for problem five. testing.
       ArrayList testOneThroughTen = new ArrayList();
       for(int i = 2; i < 11; i++)
       {
           testOneThroughTen.add(i);
       }
       smallestMultiple(testOneThroughTen);
    }
    
}
