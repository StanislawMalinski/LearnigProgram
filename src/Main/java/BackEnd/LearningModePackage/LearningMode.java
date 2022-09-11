package BackEnd.LearningModePackage;

public enum LearningMode {
    FLASHCARDS(0),
    METORITES(1),
    EXAME(2),
    WRITING(3),
    LEARN_MODE(4);

    private final int value;

    LearningMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LearningMode LearningModeFromInteger(int id) {
        switch (id) {
            case 0:
                return FLASHCARDS;
            case 1:
                return METORITES;
            case 2:
                return EXAME;
            case 3:
                return WRITING;
            case 4:
                return LEARN_MODE;
            default:
                throw new IndexOutOfBoundsException("Index can be in range from 0 to 5. (Excluding 5)");
        }
    }

    public static LearnMode getMode(LearningMode learningMode){
        switch (learningMode) {
            case FLASHCARDS -> {
                return new Flashcards();
            }
            case METORITES -> {
                return new Flashcards();
            }
            case EXAME -> {
                return new Flashcards();
            }
            case WRITING -> {
                return new Flashcards();
            }
            case LEARN_MODE -> {
                return new Flashcards();
            }
            default -> {
                return new Flashcards();
            }
        }
    }
}
