$(function () {
    const Content = {
        load: function () {
            const submenu = Menu.subMenus;
            console.log(submenu)

            submenu.forEach(menu =>{
                const path = menu.url;
                const pathName = location.pathname;

                const input = $('#overview_contentId');

                if(path == pathName){
                    const contentKey = menu.content.recKey;
                    input.val(contentKey);
                }

            })
            this.draw();
        },
        draw: function () {

            const container = $('.overview');
            const editorContent = container.find('.editor-content');

            let contentObj = [];

            AjaxUtil.request({
                url: '/api/introductions/find',
                async: false,
                success: function (data){
                    contentObj = data.result.items;
                }

            })

            contentObj.forEach(item => {
                const contentId = item.recKey;
                const contentData = item.content;

                const overviewContentId = $('#overview_contentId').val();

                if(contentId == overviewContentId){
                    editorContent.html(contentData)
                }
            })


            this.event();
        },
        event: function () {
        }
    }

    Content.load();
});