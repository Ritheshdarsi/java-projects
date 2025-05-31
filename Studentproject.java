import java.util.*;
class Student{
    String name;
    int rollnumber;
    int[] marks=new int[3];
    int total;
    float percentage;
    public Student(String name, int rollnumber,int[] marks){
        this.name=name;
        this.rollnumber=rollnumber;
        this.marks=marks;
        calculateresults();
    }
    private void calculateresults(){
        total=0;
        for(int mark:marks){
            total+=mark;
        }
        percentage=total/3.0f;
    }
	public void display(){
		System.out.println("roll no:"+rollnumber);
		System.out.println("name:"+name);
		System.out.println("marks:subject1:"+marks[0]+",subject2:"+marks[1]+",subject3:"+marks[2]);
		System.out.println("total:"+total);
		System.out.println("percentage:"+percentage+"%");
	}
}
class Studentproject{
    public static void main(String[] args){
        ArrayList<Student> students=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        int choice;
        do{
            System.out.println("1.add student  2.display all students  3.exit");
            System.out.println("enter choice:");
            choice=scanner.nextInt();
            switch(choice){
                case 1:
                    scanner.nextLine();
                    System.out.print("enter name:");
                    String name=scanner.nextLine();
                    System.out.print("enter roll number:");
                    int roll=scanner.nextInt();
                    int[] marks=new int[3];
                    for(int i=0;i<3;i++){
                        System.out.print("enter marks for subject"+(i+1)+":");
                        marks[i]=scanner.nextInt();
                    }
                    students.add(new Student(name,roll,marks));
                    break;
                case 2:
                    for(Student s:students){
                        s.display();
                    }
                    break;
                case 3:
                    System.out.println("exit program");
                default:System.out.println("invalid choice!");
            }
        }while(choice!=3);
        scanner.close();
    }
}