$(function () {
    const Content = {
        categorys: [],
        subCategorys: [],
        params: {},
        load: function (params) {
            this.params = params;
            this.event();
        },

        event: function () {

            const paramValue = this.params.key
            if(paramValue !== ""){
                AjaxUtil.requestBody({
                    url: '/api/introductions/findSelf',
                    data: {
                        key: paramValue,
                    },
                    success: function (data) {
                        console.log(data)
                        $(".pageSub #category").val(data.result.items[0].category);
                        $(".pageSub #sub_category").val(data.result.items[0].subcategory);
                        $(".pageSub #title").val(data.result.items[0].title);
                        $(".pageSub #sub_title").val(data.result.items[0].subtitle);
                        $(".pageSub #contents").val(data.result.items[0].content);

                        if (data.code == 200) {
                        } else {
                            Swal.fire({
                                icon: 'error',
                                html: "소개글 조회가 실패하였습니다.",
                            })
                        }
                    }
                })
            }

            const buttons = document.querySelectorAll("button");

            buttons.forEach(function (button) {
                button.addEventListener("click", function () {
                    const action = button.getAttribute("data-action");
                    if (action === "add") {
                        var manager_id = $("#manager_id").val();
                        var manager_name = $("#manager_name").val();
                        var manager_role = $("#manager_role").val();
                        var manager_enable = $("#manager_enable").val();

                        if(paramValue === ""){
                            AjaxUtil.requestBody({
                                url: '/api/manager/add',
                                data: {
                                    id: manager_id,
                                    name: manager_name,
                                    role: manager_role,
                                    enable: manager_enable,
                                },
                                table: 'table',
                                successMessage: '관리자가 성공적으로 추가되었습니다!'
                            })
                        }else if(paramValue !== ""){

                            AjaxUtil.requestBody({
                                url: '/api/introductions/update',
                                data: {
                                    title: titleValue,
                                    sub_title: sub_titleValue,
                                    contents: contentsValue,
                                    category: categoryValue,
                                    sub_category: sub_categoryValue,
                                    key: paramValue
                                },
                                success: function (data) {
                                    console.log(data)
                                    if (data.code == 200) {
                                        Swal.fire({

                                            icon: 'success',
                                            html: "소개글 등록이 완료되었습니다.",
                                        })
                                    } else {
                                        Swal.fire({
                                            icon: 'error',
                                            html: "소개글 등록이 실패하였습니다.",
                                        })
                                    }
                                }
                            })
                        }}
                    else if(action === "list"){
                        location.href = '/admin/accounts'
                    }
                    else if(action === "delete"){
                        AjaxUtil.requestBody({
                            url: '/api/introductions/del',
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



                });
            });

        }
    };
    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
})