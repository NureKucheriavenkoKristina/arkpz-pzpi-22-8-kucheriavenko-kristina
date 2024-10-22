class Animal:
    """
    Клас, що представляє базову тварину.
    """

    def __init__(self, name: str, age: int):
        """
        Ініціалізує новий екземпляр тварини.

        :param name: Ім'я тварини.
        :param age: Вік тварини.
        """
        self.name = name
        self.age = age

    def speak(self):
        """
        Метод, що визначає, як тварина видає звук.

        :return: Звук тварини.
        """
        return "..."

    def get_info(self):
        """
        Метод, що повертає інформацію про тварину.

        :return: Інформація про тварину у вигляді рядка.
        """
        return f"{self.name} is {self.age} years old."

    def __str__(self):
        """
        Повертає строкове представлення об'єкта.

        :return: Строкове представлення тварини.
        """
        return f"Animal(name={self.name}, age={self.age})"


class Dog(Animal):
    """
    Клас, що представляє собаку, яка є підкласом тварини.
    """

    def __init__(self, name: str, age: int, breed: str):
        """
        Ініціалізує новий екземпляр собаки.

        :param name: Ім'я собаки.
        :param age: Вік собаки.
        :param breed: Порода собаки.
        """
        super().__init__(name, age)
        self.breed = breed

    def speak(self):
        """
        Переопреділений метод для собаки.

        :return: Звук собаки.
        """
        return "Woof!"

    def fetch(self, item: str):
        """
        Метод, що симулює команду для собаки принести предмет.

        :param item: Предмет, який потрібно принести.
        :return: Повідомлення про те, що собака принесла предмет.
        """
        return f"{self.name} fetches the {item}."

    def __str__(self):
        """
        Повертає строкове представлення об'єкта.

        :return: Строкове представлення собаки.
        """
        return f"Dog(name={self.name}, age={self.age}, breed={self.breed})"


class Cat(Animal):
    """
    Клас, що представляє кота, який є підкласом тварини.
    """

    def __init__(self, name: str, age: int, color: str):
        """
        Ініціалізує новий екземпляр кота.

        :param name: Ім'я кота.
        :param age: Вік кота.
        :param color: Колір шерсті кота.
        """
        super().__init__(name, age)
        self.color = color

    def speak(self):
        """
        Переопреділений метод для кота.

        :return: Звук кота.
        """
        return "Meow!"

    def climb(self, surface: str):
        """
        Метод, що симулює дію кота на поверхні.

        :param surface: Поверхня, на яку кіт підіймається.
        :return: Повідомлення про те, що кіт підіймається на поверхню.
        """
        return f"{self.name} climbs the {surface}."

    def __str__(self):
        """
        Повертає строкове представлення об'єкта.

        :return: Строкове представлення кота.
        """
        return f"Cat(name={self.name}, age={self.age}, color={self.color})"


class Bird(Animal):
    """
    Клас, що представляє птаха, який є підкласом тварини.
    """

    def __init__(self, name: str, age: int, species: str):
        """
        Ініціалізує новий екземпляр птаха.

        :param name: Ім'я птаха.
        :param age: Вік птаха.
        :param species: Вид птаха.
        """
        super().__init__(name, age)
        self.species = species

    def speak(self):
        """
        Переопреділений метод для птаха.

        :return: Звук птаха.
        """
        return "Chirp!"

    def fly(self, distance: int):
        """
        Метод, що симулює політ птаха.

        :param distance: Відстань польоту в метрах.
        :return: Повідомлення про те, як далеко птах летить.
        """
        return f"{self.name} flies {distance} meters."

    def __str__(self):
        """
        Повертає строкове представлення об'єкта.

        :return: Строкове представлення птаха.
        """
        return f"Bird(name={self.name}, age={self.age}, species={self.species})"


def main():
    """
    Основна функція, що демонструє роботу класів Animal, Dog, Cat та Bird.
    """
    dog = Dog(name="Buddy", age=3, breed="Golden Retriever")
    cat = Cat(name="Whiskers", age=2, color="Black")
    bird = Bird(name="Tweety", age=1, species="Canary")

    animals = [dog, cat, bird]

    # Виводимо інформацію про всіх тварин і їхні звуки
    for animal in animals:
        print(animal)  # Виводимо інформацію про тварину
        print(animal.speak())  # Тварина видає звук

    # Демонструємо специфічні дії для кожної тварини
    print(dog.fetch("ball"))  # Собака приносить м'яч
    print(cat.climb("tree"))  # Кіт підіймається на дерево
    print(bird.fly(100))  # Птах летить 100 метрів


if __name__ == "__main__":
    main()
