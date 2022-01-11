package strategies.elves;

import entities.Child;

public class PinkElfStrategy implements ElfStrategy {
    @Override
    public void makeChanges(Child child) {
        child.setAssignedBudget(child.getAssignedBudget() + child.getAssignedBudget() * 30 / 100);
    }
}
