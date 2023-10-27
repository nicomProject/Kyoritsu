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

            var oEditors = [];
            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "contents",
                sSkinURI: "/static/js/smartEditor/SmartEditor2Skin.html",
                fCreator: "createSEditor2"
            })


            if(paramValue !== ""){
                AjaxUtil.requestBody({
                    url: '/api/notice/findSelf',
                    data: {
                        key: paramValue,
                    },
                    success: function (data) {
                        console.log(data)
                        $(".pageSub #category").val(data.result.items[0].category);
                        $(".pageSub #title").val(data.result.items[0].title);
                        $(".pageSub #contents").val(data.result.items[0].content);
                        $(".pageSub #create_user").val(data.result.items[0].createUser);
                        $(".pageSub #create_data").val(data.result.items[0].createDate);
                        $(".pageSub #hit").val(data.result.items[0].hit)

                        if (data.code == 200) {
                        } else {
                            Swal.fire({
                                icon: 'error',
                                html: "공지사항 조회가 실패하였습니다.",
                            })
                        }
                    }
                })
            }

            const card = $('.card-body');
            card.find('*[role="action"]').click(function(e){
                oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);

                const action = this.dataset.action;
                var categoryValue = $("#category").val();
                var titleValue = $("#title").val();
                var contentsValue = $("#contents").val();

                if(action === 'add'){
                    AjaxUtil.requestBody({
                        url: '/api/notice/add',
                        data: {
                            category: categoryValue,
                            title: titleValue,
                            contents: contentsValue,
                        },
                        success: function (data) {
                            console.log(data)
                            if (data.code == 200) {
                                Alert.success({text: '공지사항 등록이 완료되었습니다.'}, function(){
                                    location.href = '/admin/introductions'
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    html: "공지사항 등록이 실패하였습니다.",
                                })
                            }
                        }
                    })
                }
                else if(action === "update"){

                    AjaxUtil.requestBody({
                        url: '/api/notice/update',
                        data: {
                            category: categoryValue,
                            title: titleValue,
                            contents: contentsValue,
                            key: paramValue
                        },
                        success: function (data) {
                            console.log(data)
                            if (data.code == 200) {
                                Swal.fire({

                                    icon: 'success',
                                    html: "공지사항 수정이 완료되었습니다.",
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    html: "공지사항 수정이 실패하였습니다.",
                                })
                            }
                        }
                    })
                }
                else if(action === "list"){
                    location.href = '/admin/notices'
                }
                else if(action === "delete"){
                    AjaxUtil.requestBody({
                        url: '/api/notice/delete',
                        data: {
                            type: 'one',
                            id: paramValue
                        },
                        success: function (data) {
                            console.log(data)
                            if (data.code == 200) {
                            }
                        }
                    })
                }



            })


        }
    };
    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
})