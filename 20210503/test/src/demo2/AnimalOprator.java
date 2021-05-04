package demo2;

public class AnimalOprator {

    public void useAnimal(Animal a) {
        a.eat();
    }

    public void useAnimal(Jumpping j) {
        j.jump();
    }

    public Jumpping ret() {
        Jumpping j = new Dog();
        return j;
    }
    public Animal ret1() {
        Animal a = new Cat();
        return a;
    }
}
