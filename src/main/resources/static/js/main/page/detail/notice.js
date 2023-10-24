$(function () {
    const Content = {
        params: {},
        load: function (params) {
            this.params = params;
            console.log(this.params)
            this.event();
        },
        event: function () {
            const paramValue = this.params.key

            if(paramValue !== ""){
                AjaxUtil.requestBody({
                    url: '/api/notice/findSelf',
                    data: {
                        key: paramValue,
                    },
                    success: function (data) {
                        console.log(data)
                        $(".notice-container #title").text(data.result.items[0].title);
                        $(".notice-container #create-user").text(data.result.items[0].createUser);
                        $(".notice-container #create-date").text(data.result.items[0].createDate);
                        $(".notice-container #content").html(data.result.items[0].content);

                        if (data.code != 200) {
                            Swal.fire({
                                icon: 'error',
                                html: "공지사항 조회를 실패헸습니다.",
                            })
                        }
                    }
                })
            }
        }
    }


    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
});