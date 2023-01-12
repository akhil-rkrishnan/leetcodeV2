package interview.company.aptiv;

import java.util.ArrayList;

class Aptiv {
   /*

* Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
*/

     public static void main(String args[]) {
         ArrayList<String> strs = new ArrayList<String>();
         strs.add("flower"); strs.add("flow"); strs.add("flight");
//         strs.add("dog"); strs.add("racecar"); strs.add("car");

         if (strs.size() == 0) {
             System.out.println("");
         } else {
             String cur = strs.get(0);
             int i = 1;

             while (i < strs.size()) {
                 while (strs.get(i).indexOf(cur) != 0) {
                     cur = cur.substring(0, cur.length() - 1);
                     println(cur);
                 }
                 if (cur.isBlank() || cur.isEmpty()) {
                     break;
                 }
                 i++;
             }
             println();
             println(cur);
         }
     }

     static void println(String message) {
         System.out.println(message);
     }
    static void print(String message) {
        System.out.print(message);
    }
     static void println() {
         System.out.println();
     }
}
