$(function () {
    const Content = {
        categorys: [],
        subCategorys: [],
        params: {},
        formData: {},
        load: function (params) {
            this.params = params;
            this.event();
        },

        event: function () {
            const paramValue = this.params.key
                AjaxUtil.requestBody({
                    url: '/api/applicant/findSelf/' + this.params.key,
                    success: function (data) {
                        console.log(data)
                        $("#name").text(data.result.items[0].name);
                        $("#gneder").text(data.result.items[0].gender);
                        $("#phone").text(data.result.items[0].phone);
                        $("#email").text(data.result.items[0].email);
                        $("#birth").text(data.result.items[0].birthDate);
                        $("#nationality").text(data.result.items[0].nationality);
                        $("#contents_question").text(data.result.items[0].contents);

                        if (data.code == 200) {
                        } else {
                            Swal.fire({
                                icon: 'error',
                                html: "지원자 조회가 실패하였습니다.",
                            })
                        }
                    }
                })

            const buttons = document.querySelectorAll("button");

            buttons.forEach(function (button) {
                button.addEventListener("click", function () {
                    const action = button.getAttribute("data-action");
                    if (action === "add" && validateField(formData)) {
                        var manager_id = $("#manager_id").val();
                        var manager_name = $("#manager_name").val();
                        var manager_role = $("#contents_answer").val();
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
                                success: function (data) {
                                    if (data.code == 200) {
                                        Alert.success({text: '관리자 계정이 등록되었습니다.'}, function(){
                                            location.href = '/admin/accounts'
                                        })
                                    } else if(data.code === 210){
                                        Alert.warning({text: data.desc})
                                    }
                                    else{
                                        Alert.error({text: data.desc});
                                    }
                                }
                            })
                        }}
                    else if(action === "list"){
                        location.href = '/admin/accounts'
                    }
                });
            });
        }
    };
    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
})