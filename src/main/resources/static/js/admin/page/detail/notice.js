$(function(){
    const Content = {
        params: {},
        load: function(params){
            this.params = params;
            console.log(params);
        },
    };

    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
})