import java.util.*;
import java.io.*;
public class Question implements java.io.Serializable
{
    public static Scanner in = new Scanner(System.in);
    public String question;
    public Question parent = null;
    public Question yes = null;
    public Question no = null;
    public boolean terminal = false;
    public String answer;
    
    public Question(String q){
        this.question=q;
    }
    
    public void learn(){
        System.out.print("I give up! What were you thinking of? ");
        String ans=in.nextLine();
        System.out.print("What is a question to which the answer is 'yes' for "+question+" but 'no' for "+ans+"?\nQuestion: ");
        String newQ=in.nextLine();
        
        Question newQuestion = new Question(newQ);
        Question newAnswer = new Question(ans);
        newAnswer.terminal=true;
        newAnswer.answer="no";
        
        newQuestion.parent=this.parent;
        newQuestion.yes=this;
        this.parent=newQuestion;
        
        if(this.answer.equals("yes")){
            newQuestion.parent.yes=newQuestion;
        }
        else
        {
            newQuestion.parent.no=newQuestion;
        }
        
        newQuestion.no=newAnswer;
        newAnswer.parent=newQuestion;
    }
    
    public void ask(){
        if(terminal){
            System.out.print("Are you thinking of "+question+"? (yes/no): ");
        }
        else{
            System.out.print(question + " (yes/no): ");
        }
        String ans=in.nextLine();
        if(ans.equals("yes")){
            if(yes==null){
                System.out.println("Hooray, I've won!");
            }
            else
            {
                yes.ask();
            }
        }
        else
        {
            if(no==null){
                learn();
            }
            else
            {
                no.ask();
            }
        }
    }
    
    public void printQuestion(String space){
        System.out.println(space+question);
        if(yes!=null){
            yes.printQuestion(space+space);
        }
        if(no!=null){
            no.printQuestion(space+space);
        }
    }
}