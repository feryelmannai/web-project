// Script pour la fonctionnalité de recherche
document.getElementById("searchTrigger").addEventListener("click", function () {
  document.getElementById("searchBox").classList.toggle("active");
});

// Slideshow functionality
document.addEventListener("DOMContentLoaded", function () {
  const slides = document.querySelectorAll(".slide");
  let currentSlide = 0;

  function nextSlide() {
    slides[currentSlide].classList.remove("active");
    currentSlide = (currentSlide + 1) % slides.length;
    slides[currentSlide].classList.add("active");
  }

  // Change slide every 5 seconds
  setInterval(nextSlide, 4000);

  // Animation lettre par lettre
  const h1 = document.getElementById("animated-title");
  if (h1) {
    const words = h1.textContent.split(" ");
    h1.innerHTML = "";

    words.forEach((word, wordIndex) => {
      const wordSpan = document.createElement("span");
      wordSpan.className = "word";

      // Ajouter chaque lettre du mot
      for (let i = 0; i < word.length; i++) {
        const letterSpan = document.createElement("span");
        letterSpan.className = "letter";
        letterSpan.textContent = word[i];
        letterSpan.style.animation = `letterAnimation 0.5s ease-out forwards ${
          wordIndex * 0.2 + i * 0.05
        }s`;
        wordSpan.appendChild(letterSpan);
      }

      h1.appendChild(wordSpan);

      // Ajouter un espace entre les mots (sauf après le dernier mot)
      if (wordIndex < words.length - 1) {
        const spaceSpan = document.createElement("span");
        spaceSpan.className = "space";
        spaceSpan.innerHTML = "&nbsp;";
        h1.appendChild(spaceSpan);
      }
    });
  }
});
