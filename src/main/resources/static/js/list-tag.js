(function () {
  const csrfHeader = document.querySelector(
      'meta[name="_csrf_header"]').content;
  const csrfToken = document.querySelector('meta[name="_csrf"]').content;

  const headers = {
    'Accept': 'application/json, text/plain, */*',
    'Content-type': 'application/json',
    [csrfHeader]: csrfToken
  };

  bindEventListeners();

  function saveTag(e) {
    e.preventDefault();

    const tagName = document.getElementById('tagNameInput').value;

    fetch('/admin/tag/', {
      method: 'POST',
      headers: headers,
      body: JSON.stringify({
        'name': tagName
      })
    })
    .then(checkResponse)
    .then(reloadTagTable)
    .catch(handleUniqueException);

  }

  function deleteTag(e) {
    e.preventDefault();

    const tagId = e.target.closest('a').getAttribute('data-tag-id');

    fetch('/admin/tag/' + tagId, {
      method: 'DELETE',
      headers: headers
    })
    .then(checkResponse)
    .then(reloadTagTable)
    .catch(handleUsedTagException);
  }

  // Utils

  function bindEventListeners() {
    document.getElementById('save-tag').addEventListener('click', saveTag);
    document.getElementsByName('delete-tag')
    .forEach(
        deleteTagLink => deleteTagLink.addEventListener('click', deleteTag));
  }

  function cleanTagNameInput() {
    const tagNameInput = document.getElementById('tagNameInput');
    tagNameInput.value = "";
    tagNameInput.classList.remove("is-invalid");
  }

  function checkResponse(res) {
    if (res.ok) {
      cleanTagNameInput();
      return res.text()
    } else {
      throw res;
    }
  }

  function reloadTagTable(data) {
    document.getElementById("tags-list-table").parentElement.innerHTML = data;
    feather.replace();
    bindEventListeners();
  }

  function handleUniqueException(ex) {
    if (ex.status === 409) {
      ex.json().then(ex => {
        const tagNameInput = document.querySelector('#tagNameInput');
        tagNameInput.classList.add('is-invalid');
      });
    }
  }

  function handleUsedTagException(ex) {
    if (ex.status === 409) {
      ex.json().then(ex => {
        $('#deleteExceptionModal').modal('show');
      });
    }
  }

})();