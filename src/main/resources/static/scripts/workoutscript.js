// Get the date display elements and navigation arrows
const dateDisplay = document.getElementById('dateDisplay');
const prevArrow = document.getElementById('prevArrow');
const nextArrow = document.getElementById('nextArrow');

let currentDate = new Date(); // Variable to store the current date value

// Function to display the current date or "Today"
function displayDate() {
  const today = new Date();
  const options = { month: 'long', day: 'numeric', year: 'numeric' };

  if (currentDate.toDateString() === today.toDateString()) {
    dateDisplay.textContent = 'Today';
  } else {
    const formattedDate = currentDate.toLocaleDateString(undefined, options);
    dateDisplay.textContent = formattedDate;
  }
}

// Function to navigate to the previous date
function goToPreviousDate() {
  currentDate.setDate(currentDate.getDate() - 1);
  displayDate();
}

// Function to navigate to the next date
function goToNextDate() {
  currentDate.setDate(currentDate.getDate() + 1);
  displayDate();
}

// Event listeners for navigation arrows
prevArrow.addEventListener('click', goToPreviousDate);
nextArrow.addEventListener('click', goToNextDate);

// Display the current date initially
displayDate();