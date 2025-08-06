import java.util.ArrayList;
import java.util.List;

class Person {
    String name;
    List<Person> children;

    Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    void addChild(Person child) {
        children.add(child);
    }
}

class FamilyTree {

    public void preorderTraversal(Person person) {
        if (person == null) return;
        System.out.println(person.name);
        for (Person child : person.children) {
            preorderTraversal(child);
        }
    }

    public void postorderTraversal(Person person) {
        if (person == null) return;
        for (Person child : person.children) {
            postorderTraversal(child);
        }
        System.out.println(person.name);
    }

    public void inorderTraversal(Person person) {
        if (person == null) return;
        int mid = person.children.size() / 2;
        for (int i = 0; i < mid; i++) {
            inorderTraversal(person.children.get(i));
        }
        System.out.println(person.name);
        for (int i = mid; i < person.children.size(); i++) {
            inorderTraversal(person.children.get(i));
        }
    }
}

public class TreeProject {
    public static void main(String[] args) {
        Person grandfather = new Person("venkateshwarlu");
        Person grandmother = new Person("ratnamma");

        Person father = new Person("ramesh");
        Person mother = new Person("gowri");

        Person uncle = new Person("ashok");
        Person aunt = new Person("aruna");

        Person child1 = new Person("rithesh");
        Person child2 = new Person("abhilash");

        grandfather.addChild(father);
        grandfather.addChild(uncle);

        father.addChild(child1);
        father.addChild(child2);

        FamilyTree tree = new FamilyTree();

        System.out.println("Pre-order Traversal:");
        tree.preorderTraversal(grandfather);

        System.out.println("\nPost-order Traversal:");
        tree.postorderTraversal(grandfather);

        System.out.println("\nIn-order Traversal:");
        tree.inorderTraversal(grandfather);
    }
}
