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
  fetch("https://script.google.com/macros/s/AKfycbxE4vXNf0wWGDNEbYaUESyoWR6tfgvuBsBDKPAWPPwvKnlv5tsBFZo6ugChJFmo66NG2w/exec", {
    redirect: "follow",
    method: "POST",
    body: JSON.stringify(data),
    headers: { "Content-Type": "text/plain;charset=utf-8" },
  })
    .then((res) => res.json())
    .then((res) => {
      if (res.result === "success") {
        showAlert("Pesan berhasil dikirim!");
        form.reset();
      } else {
        showAlert("Gagal mengirim pesan.", true);
      }
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
