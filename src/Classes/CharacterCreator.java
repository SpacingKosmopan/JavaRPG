package Classes;

public class CharacterCreator {
    // 1. Name your character
    // 2. Choose gender
    // 3. Choose class
    // a. mage

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
        Class heroClass;
        Gender heroGender;

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