package Classes;

public class CharacterCreator {
    public enum Class {
        Mage,
        Knight,
        Archer
    }

    public enum Gender {
        Male,
        Female
    }

    public static class Character {
        public Class heroClass;
        public Gender heroGender;

        public String pronoun(String maleCase, String femaleCase) {
            return heroGender == Gender.Male ? maleCase : femaleCase;
        }

        public Character(Class heroClass, Gender heroGender) {
            this.heroClass = heroClass;
            this.heroGender = heroGender;
        }

        public Character() {
            this.heroClass = null;
            this.heroGender = null;
        }
    }
}