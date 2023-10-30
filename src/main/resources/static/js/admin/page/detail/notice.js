$(function () {
    const Content = {
        params: {},
        formData: {},
        load: function (params) {
            this.params = params;
            console.log(this.params)
            this.event();
        },
        event: function () {

            function validateField(formData) {
                for (const field in formData) {
                    console.log(field)
                    const value = document.getElementById(field).value;
                    if(!value){
                        Alert.warning({text: `${formData[field]}은 필수 입력 항목입니다.`})
                        return false
                    }
                }
                return true;


            }

            const paramValue = this.params.key
            formData = {'title' : '제목', 'contents' : '본문'};

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

                if(action === 'add' && validateField(formData)){
                    AjaxUtil.requestBody({
                        url: '/api/notice/add',
                        data: {
                            category: categoryValue,
                            title: titleValue,
                            contents: contentsValue,
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                Alert.success({text: '공지사항 등록이 완료되었습니다.'}, function(){
                                    location.href = '/admin/notices'
                                })
                            } else if(data.code === 210){
                                Alert.warning({text: data.desc})
                            }
                            else{
                                Alert.error({text: data.desc});
                            }
                        }
                    })
                }
                else if(action === "update" && validateField(formData)){

                    AjaxUtil.requestBody({
                        url: '/api/notice/update',
                        data: {
                            category: categoryValue,
                            title: titleValue,
                            contents: contentsValue,
                            key: paramValue
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                Alert.success({text: '공지사항이 수정되었습니다.'}, function(){
                                    location.href = '/admin/notices'
                                })
                            } else if(data.code === 210){
                                Alert.warning({text: data.desc})
                            }
                            else{
                                Alert.error({text: data.desc});
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
                            if(data.code === 200){
                                Alert.success({text: data.desc}, function (){
                                    location.href = '/admin/notices'
                                })
                            }
                            else{
                                Alert.error({text: data.desc});
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