$(function () {
    const Content = {
        categorys: [],
        subCategorys: [],
        params: {},
        formData: {},
        load: function (params) {
            const that = this;
            const category = $("#category");
            const sub_category = $("#sub_category");
            let items = [];
            let paramValue = params.key
            let hi = ""


            AjaxUtil.request({
                url: '/api/main/setting/category',
                async: false,
                success: function (data) {
                    items = data.result.items;
                }
            });

            const categoryHash = {};
            items.map(e => e.menu).forEach(group => {
                categoryHash[group.recKey] = group;
            });
            Object.keys(categoryHash).forEach(key => {
                category.append($('<option>', {
                        value: key,
                        text: categoryHash[key].name,
                    }
                ));
            });
                const changeFunc = function(){
                    sub_category.empty();
                    items.forEach(item => {
                        if(Number(category.val()) === item.menu.recKey){
                            sub_category.append($('<option>', {
                                value: item.recKey,
                                text: item.name
                            }));
                        }
                    });
                }
            changeFunc();


            category.on('change', function() {
                    changeFunc();
                });

            if(paramValue !== ""){
                AjaxUtil.requestBody({
                    url: '/api/introductions/findSelf',
                    data: {
                        key: paramValue,
                    },
                    success: function (data) {
                        $(".pageSub #category").val(data.result.items[0].category);
                        $(".pageSub #sub_category").val(data.result.items[0].subcategory);
                        $(".pageSub #title").val(data.result.items[0].title);
                        $(".pageSub #sub_title").val(data.result.items[0].subtitle);
                        $(".pageSub #contents").val(data.result.items[0].content);
                        // console.log($(".pageSub #contents").val());

                        changeFunc();

                        console.log(data)

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
            this.params = params;
            console.log(this.params)
            this.event();
        },

        event: function () {
            var oEditors = [];

            formData = {'title' : '제목', 'sub_title' : '소제목', 'contents' : '본문'};

            // nhn.husky.EZCreator.createInIFrame({
            //     oAppRef: oEditors,
            //     elPlaceHolder: "contents",
            //     sSkinURI: "/static/js/smartEditor/SmartEditor2Skin.html",
            //     fCreator: "createSEditor2"
            // })
            var img = new Image();
            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "contents",
                sSkinURI: "/static/js/smartEditor/SmartEditor2Skin.html",
                fOnAppLoad : function(){
                    fn_checkClipboard();//추가한 함수
                },
                fCreator: "createSEditor2"
            });

            function urlToBlob(url) {
                return fetch(url).then(response => {
                    if (response.status === 200) {
                        return response.blob();
                    }
                    throw new Error('Network response was not ok.');
                });
            }

            function dataURLtoBlob(dataURL) {
                // Base64 데이터를 디코딩합니다.
                var byteString = atob(dataURL.split(',')[1]);

                // 문자열을 ArrayBuffer로 변환합니다.
                var ab = new ArrayBuffer(byteString.length);
                var ia = new Uint8Array(ab);
                for (var i = 0; i < byteString.length; i++) {
                    ia[i] = byteString.charCodeAt(i);
                }

                // ArrayBuffer를 Blob 객체로 변환합니다.
                var blob = new Blob([ab], { type: 'image/png' }); // 이미지 유형을 필요에 따라 변경할 수 있습니다.

                return blob;
            }

            // function checkForImg() {
            //     var imgElements = target_se2.querySelectorAll("img");
            //
            //     imgElements.forEach(function(img, index){
            //         var imgSrc = img.getAttribute("src");
            //
            //         // imgSrc가 데이터 URL이라면
            //         if (imgSrc.startsWith('data:')) {
            //             var blob = dataURLtoBlob(imgSrc); // 이전에 설명한 dataURLtoBlob 함수를 사용
            //             formData.append('images', blob, 'image' + index + '.png');
            //         }
            //         // 그렇지 않고 외부 URL이라면
            //         else {
            //             // URL에서 Blob을 얻어 FormData에 추가하는 로직이 필요합니다.
            //             // 이 부분은 CORS 정책에 따라 동작하지 않을 수도 있습니다.
            //             urlToBlob(imgSrc).then(blob => {
            //                 formData.append('images', blob, 'image' + index + '.png');
            //             }).catch(error => console.error('Error fetching image:', error));
            //         }
            //     });
            //
            //     // ... 나머지 xhr 요청 로직 ...
            // }

            function fn_checkClipboard() {
                console.log("asdasdasd")
                var target_se2 = document.querySelector("iframe").contentWindow.document.querySelector("iframe").contentWindow.document.querySelector(".se2_inputarea");

                var observer = new MutationObserver(function (mutationsList) {
                    mutationsList.forEach(function (mutation) {
                        if (mutation.type === "childList" || mutation.type === "characterData") {
                            // 변경된 노드를 모두 가져오기
                            var changedNodes = Array.from(target_se2.querySelectorAll("p"));
                            // 각 <p> 태그의 내용을 콘솔에 출력
                            changedNodes.forEach(function (pElement) {
                                console.log("값이 변경되었습니다:", pElement.innerHTML);
                            });

                            checkForImg();
                        }
                    });
                });


                observer.observe(target_se2, { subtree: true, characterData: true, childList: true });

                function checkForImg() {
                    var imgElements = target_se2.querySelectorAll("img");
                    var formData = new FormData();
                    if(imgElements.length > 0){
                    imgElements.forEach(function(img, index){
                        var imgSrc = img.getAttribute("src");


                        console.log(imgSrc)

                        // imgSrc가 데이터 URL이라면
                        if (imgSrc.startsWith('data:')) {
                            var blob = dataURLtoBlob(imgSrc); // 이전에 설명한 dataURLtoBlob 함수를 사용
                            console.log("blob",blob)
                            console.log("URL blob size = " + blob.size)
                            formData.append('images', blob, 'image' + blob.size + '.png');
                        }
                        // 그렇지 않고 외부 URL이라면
                        else {
                            console.log("test")
                            // URL에서 Blob을 얻어 FormData에 추가하는 로직이 필요합니다.
                            // 이 부분은 CORS 정책에 따라 동작하지 않을 수도 있습니다.
                            urlToBlob(imgSrc).then(blob => {
                                formData.append('images', blob, 'image' + index + '.png');
                            }).catch(error => console.error('Error fetching image:', error));
                        }
                    });
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', '/api/uploadImages', true); // 서버의 업로드 엔드포인트로 변경해야 합니다.

                        xhr.onload = function () {
                            if (xhr.status === 200) {
                                console.log('서버 응답:', xhr.responseText);
                                var imageUrl = xhr.responseText;

                                var changedNodes = Array.from(target_se2.querySelectorAll("p"));
                                changedNodes.forEach(function (pElement) {
                                    // pElement.innerHTML = '<img src="' + imageUrl + '">';
                                    var imageSrc = "/static/images/image/" + imageUrl;
                                    pElement.innerHTML = '<img src="' + imageSrc + '">';

                                });
                            } else {
                                console.error('서버 응답 에러:', xhr.status);
                            }
                        };

                    xhr.send(formData);
                }
                }
            }


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


            const buttons = document.querySelectorAll("button");
            const paramKey = this.params.key
            // 모든 버튼에 클릭 이벤트 리스너를 추가합니다.
            buttons.forEach(function (button) {
                button.addEventListener("click", function () {
                    // data-action 속성을 확인하여 해당 동작을 처리합니다.
                    const action = button.getAttribute("data-action");
                    oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);


                    if (action === "add") {
                        var titleValue = $("#title").val();
                        var sub_titleValue = $("#sub_title").val();
                        var contentsValue = $("#contents").val();
                        var categoryValue = $("#category").val();
                        var sub_categoryValue = $("#sub_category").val();

                        console.log(contentsValue)


                        if(paramKey === "" && validateField(formData)){
                            AjaxUtil.requestBody({
                                url: '/api/introductions/add',
                                data: {
                                    title: titleValue,
                                    sub_title: sub_titleValue,
                                    contents: contentsValue,
                                    category: categoryValue,
                                    sub_category: sub_categoryValue
                                },
                                success: function (data) {
                                    if(data.code === 200){
                                        Alert.success({text: data.desc}, function (){
                                            location.href = '/admin/introductions'
                                        })
                                    }else if(data.code === 210){
                                        Alert.warning({text: data.desc})
                                    }
                                    else{
                                        Alert.error({text: data.desc});
                                    }
                                }
                            })
                        }else if(paramKey !== "" && validateField(formData)){
                            AjaxUtil.requestBody({
                                url: '/api/introductions/update',
                                data: {
                                    title: titleValue,
                                    sub_title: sub_titleValue,
                                    contents: contentsValue,
                                    category: categoryValue,
                                    sub_category: sub_categoryValue,
                                    key: paramKey
                                },
                                success: function (data) {
                                    console.log(data)
                                    if(data.code === 200){
                                        Alert.success({text: data.desc}, function (){
                                            location.href = '/admin/introductions'
                                        })
                                    }else if(data.code === 210){
                                        Alert.warning({text: data.desc})
                                    }
                                    else{
                                        Alert.error({text: data.desc});
                                    }
                                }
                            })
                        }}
                    else if(action === "list"){
                        location.href = '/admin/introductions'
                    }
                    else if(action === "delete"){
                        AjaxUtil.requestBody({
                            url: '/api/introductions/delete',
                            data: {
                                type: 'one',
                                id: paramKey
                            },
                            success: function (data) {
                                if(data.code === 200){
                                    Alert.success({text: data.desc}, function (){
                                        location.href = '/admin/introductions'
                                    })
                                }
                                else{
                                    Alert.error({text: data.desc});
                                }
                            }
                        })
                    }
                });
            });

        }
    };
    Content.load({
        key: $('.param[name="key"]').val() || '',
    });
})