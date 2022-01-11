package strategies.elves;

import entities.Child;

public class BlackElfStrategy implements ElfStrategy {
    @Override
    public void makeChanges(Child child) {
        child.setAssignedBudget(child.getAssignedBudget() - child.getAssignedBudget() * 30 / 100);
    }
}
