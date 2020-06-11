(function () {
  // Markdown Processing Init
  const simplemde = new SimpleMDE({element: document.getElementById("markdown-editor-area")});

  // Utils
  function getValueFromId(id) {
    let element = document.getElementById(id);
    return element.value || element.innerText;
  }

  const form = document.getElementById("post-form");
  const saveBtn = document.getElementById("save");
  const markdownTextArea = document.getElementById("markdown-editor-area");
  saveBtn.addEventListener("click", (e) => {
    e.preventDefault();
    markdownTextArea.value = simplemde.value();
    form.submit();
  });
})();