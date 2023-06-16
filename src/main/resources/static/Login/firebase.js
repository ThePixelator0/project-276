// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.2/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.22.2/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDLondxw0nlbVwHIBWdClrg_IbbqG3UBVQ",
  authDomain: "cmpt276-project-f6d05.firebaseapp.com",
  projectId: "cmpt276-project-f6d05",
  storageBucket: "cmpt276-project-f6d05.appspot.com",
  messagingSenderId: "464995767063",
  appId: "1:464995767063:web:aa79f42c9faf56041c1d52",
  measurementId: "G-X19HENLTMD"
};

// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const analytics = getAnalytics(app);
