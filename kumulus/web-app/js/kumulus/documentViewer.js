function DocumentViewer(documentId) {
    this.id = documentId;
    this.window = window.open(url("document", "view", documentId));
    this.window.focus();
}