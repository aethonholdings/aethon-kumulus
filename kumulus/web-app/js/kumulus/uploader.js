
function Uploader(element) {
    this.form = element;
    this.inputButtons = element.children(".kumulus-uploader");
    
    this.enable = function(node) {
        this.form.attr("action", url("fileUploader", "process", node.data.key));
        this.inputButtons.attr("disabled", false);
        this.inputButtons.removeClass("pure-button-disabled");
        this.inputButtons.addClass("pure-button-enabled");
    }
    
    this.disable = function() {
        this.form.attr("action", url("fileUploader", "process", "-1"));
        this.inputButtons.attr("disabled", true);
        this.inputButtons.removeClass("pure-button-enabled");
        this.inputButtons.addClass("pure-button-disabled");
    }
    
}
