const form = document.querySelector("form.p");
const popup = document.getElementById("popup");
const alertBox = document.getElementById("alertBox");

let formData = {};

form.addEventListener("submit", function (e) {
  e.preventDefault();
  console.log("Tombol diklik");
  const nama = form.querySelector("input").value.trim();
  const pesan = form.querySelector("textarea").value.trim();
  if (!nama || !pesan) {
    showAlert("Nama dan pesan wajib diisi!", true);
    return;
  }

  formData = { nama, pesan };
  popup.style.display = "flex"; // Tampilkan popup konfirmasi
});

document.getElementById("confirmSend").onclick = () => {
  popup.style.display = "none";
  sendDataToSheet(formData);
};

document.getElementById("cancelSend").onclick = () => {
  popup.style.display = "none";
};

function sendDataToSheet(data) {
  fetch("https://script.google.com/macros/s/AKfycbzv1zYNWtf8ki4DV85eyLkcKZuHhAM0fOXK-fMw3IKX9uHv_bIv_LJ8xb6d1mYw1X4L/exec", {
    method: "POST",
    mode: "no-cors",
    body: JSON.stringify(data),
    headers: { "Content-Type": "application/json" },
  })
    .then(() => {
      showAlert("Pesan berhasil dikirim! (tanpa respons konfirmasi)");
      form.reset(); // pastikan variabel 'form' sudah dideklarasikan
    })
    .catch(() => {
      showAlert("Terjadi kesalahan jaringan.", true);
    });
}

function showAlert(msg, isError = false) {
  alertBox.textContent = msg;
  alertBox.className = "alert" + (isError ? " error" : "");
  alertBox.style.display = "block";
  setTimeout(() => (alertBox.style.display = "none"), 3000);
}

function myFunction(x) {
  x.classList.toggle("change");
  var menu = document.querySelector(".menu");
  if (menu.style.display === "flex") {
    menu.style.display = "none";
  } else {
    menu.style.display = "flex";
  }
}

window.addEventListener("resize", function () {
  var menu = document.querySelector(".menu");
  var container = document.querySelector('.container');
  if (window.innerWidth > 1280) {
    menu.style.display = "flex";
  } else {
    menu.style.display = "none";
    container.classList.remove('change');
  }
});
