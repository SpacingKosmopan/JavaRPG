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

    public class Character {
        Class heroClass;
        String heroName;
        Gender heroGender;

        public String pronoun(Gender g, String maleCase, String femaleCase) {
            return g == Gender.Male ? maleCase : femaleCase;
        }

        public Character(Class heroClass, String heroName, Gender heroGender) {
            this.heroClass = heroClass;
            this.heroName = heroName;
            this.heroGender = heroGender;
        }
    }
}