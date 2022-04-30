package javatest;

class EmpInfo{
	EmpInfo(int a, String s)
	{
	id=a;
	name=s;
	}
	
	int id;
    String name;
    public EmpInfo() {
        // TODO Auto-generated constructor stub
    }
    void display() {
        System.out.println(id+" "+name);
    }
}
public class constructorDemo {
    public static void main(String[] args) {
        EmpInfo emp1=new EmpInfo(1,"Wazir");
        EmpInfo emp2=new EmpInfo();
        emp1.display();
        emp2.display();
    }
}