document.addEventListener("DOMContentLoaded", function () {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", (e) => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }

  document.querySelectorAll(".form-group--dropdown select").forEach((el) => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function (e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach((el) => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      this.$formSteps = form.querySelectorAll(".form-step");
      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach((btn) => {
        btn.addEventListener("click", (e) => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach((btn) => {
        btn.addEventListener("click", (e) => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", (e) => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      this.$formSteps.forEach((step) => {
        step.classList.remove("active");

        if (step.dataset.step == this.currentStep) {
          step.classList.add("active");
        }
      });

      this.$stepInstructions.forEach((step) => {
        step.parentElement.hidden = step.dataset.step != this.currentStep;
      });

      if (this.currentStep === 5) {
        const categories = Array.from(document.querySelectorAll('input[name="categories"]:checked')).map((input) => input.dataset.name);
        const quantity = document.querySelector('input[name="quantity"]').value;
        const institution = document.querySelector('input[name="institution"]:checked').dataset.name;
        const street = document.querySelector('input[name="street"]').value;
        const city = document.querySelector('input[name="city"]').value;
        const zipCode = document.querySelector('input[name="zipCode"]').value;
        const pickUpDate = document.querySelector('input[name="pickUpDate"]').value;
        const pickUpTime = document.querySelector('input[name="pickUpTime"]').value;
        const pickUpComment = document.querySelector('textarea[name="pickUpComment"]').value;

        document.querySelector('#summary-quantity').innerText = `${quantity} worków: ${categories.join(', ')}`;
        document.querySelector('#summary-institution').innerText = institution;

        document.querySelector('#summary-street').innerText = street;
        document.querySelector('#summary-city').innerText = city;
        document.querySelector('#summary-zipCode').innerText = zipCode;

        document.querySelector('#summary-pickUpDate').innerText = pickUpDate;
        document.querySelector('#summary-pickUpTime').innerText = pickUpTime;
        document.querySelector('#summary-pickUpComment').innerText = pickUpComment;
      }
    }
  }

  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});