package simulation;

import entities.Child;
import entities.ChildInputData;
import entities.ChildUpdate;
import entities.AnnualChange;
import databases.Database;
import entities.Gift;
import enums.Category;

import java.util.*;

public final class Updater extends Observable {
    public Updater() {

    }

    public void notify(AnnualChange change) {
        setChanged();
        notifyObservers(change);
    }

    /**
     * Updates the database.
     * @param currentChange that affects the database
     */
    public static void updateDatabase(final AnnualChange currentChange) {
        updateBudget(currentChange.getNewSantaBudget());
        updateGifts(currentChange.getNewGifts());
        updateChildrenList(currentChange.getNewChildren());
        updateChildren(currentChange.getChildrenUpdates());
        updateStrategy(currentChange);
    }

    public static void updateBudget(final double newBudget) {
        Database.getDatabase().setSantaBudget(newBudget);
    }

    public static void updateGifts(final ArrayList<Gift> newGifts) {
        newGifts.forEach(gift -> Database.getDatabase().getGifts().add(gift));
    }

    public static void updateChildrenList(final ArrayList<ChildInputData> newChildren) {
        Database.getDatabase().getChildren().forEach(entry -> {
            entry.increaseAge(); entry.setChildType(); });
        newChildren.forEach(child -> Database.getDatabase().getChildren().add(new Child(child)));
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
    }

    public static void updateChildren(final ArrayList<ChildUpdate> childrenUpdates) {
        for (ChildUpdate update : childrenUpdates) {
            Child child = findChild(update.getId());

            if (child == null) {
                continue;
            }

            updateNiceScore(child, update.getNiceScore());
            updateGiftsPreferences(child, update.getGiftsPreferences());

            child.setElf(update.getElf());
        }
    }

    public static Child findChild(final int id) {
        for (Child child : Database.getDatabase().getChildren()) {
            if (child.getId() != id) {
                continue;
            }

            return child;
        }

        return null;
    }

    public static void updateNiceScore(final Child child, final Double newNiceScore) {
        if (newNiceScore == null) {
            return;
        }
        child.getNiceScoreHistory().add(newNiceScore);
    }

    public static void updateGiftsPreferences(final Child child,
                                               final ArrayList<Category> newGiftsPreferences) {
        Set<Category> noDuplicatesList = new LinkedHashSet<>(newGiftsPreferences);
        newGiftsPreferences.clear();
        newGiftsPreferences.addAll(noDuplicatesList);

        newGiftsPreferences.forEach(giftType -> child.getGiftsPreferences().remove(giftType));

        newGiftsPreferences.addAll(child.getGiftsPreferences());
        child.setGiftsPreferences(newGiftsPreferences);
    }

    public static void updateStrategy(final AnnualChange currentChange) {
        Database.getDatabase().setStrategy(currentChange.getStrategy());
    }
}
