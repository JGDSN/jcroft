var loading = document.getElementById("loading");
var main_header = document.getElementById("main_header");
var title = document.getElementById("title");
var main_div = document.getElementById("main_div");
var breadcrumb = document.getElementById("breadcrumb");
var modal = document.getElementById("modal");
var modal_title = document.getElementById("modal-title");
var modal_body = document.getElementById("modal-body");
var modal_footer = document.getElementById("modal-footer");
var curr_location = "";
var page_title = "JCroft Usermanagement";
var page_title_prefix = "";
var page_title_suffix = "";

//Component variables
var component = null;




function navigate(url){
    navigate(url, false, 0, 0, {});
}
function navigate(url, back, sx, sy, old_component_data){
    if(url==="#")return;
    if(curr_location===url)return;
    if(curr_location==="")curr_location="/";
    setLoading(true);
    var backup_component = component;
    if(component){
        component.onExitComponent(url);
    }

    var ajax_path = "/a"+url;
    if(ajax_path.includes("?"))ajax_path = ajax_path.split("?")[0];
    $.get( ajax_path, function(data) {
        main_div.innerHTML = data;

        if(data.includes("<title>")||data.includes("LOGOUTIDENTIFYINGKEY")){
            //Our login page :D
            CookieUtil.set("redirect", "/p"+url, null, "/");
            window.location = "/login?error=Session%20expired&from="+encodeURIComponent("/p"+url);
            return;
        }

        eval(document.getElementById("component-script").innerHTML);

        if(back){
            component.initFromData(url, parseQuery(url), old_component_data);
        }else{
            component.initWithoutData(url, parseQuery(url));
            if(backup_component){
                updateComponentData(backup_component.getQueryData(), backup_component.getComponentData());
            }
            window.history.pushState({"path":url, "sx":window.scrollX, "sy":window.scrollY, "cd":{"unspecified": true}},"JCroft Usermanagement", "/p"+url);
        }

        setLoading(false);
        window.scrollTo(0, 0);
        curr_location = url;
        component.onInitComplete(url, back?sx:-1, back?sy:-1);
    })
        .done(function() {
            //Maybe some code in the future
        })
        .fail(function() {
            showModal( "danger", "Error while contacting JCroft", "<p>Error while performing AJAX request. Maybe try reloading the page?</p>", [{name:"Close", action:"hideModal();", class:"pull-left"}, {name:"Reload", action:"window.location.reload(true);"}]);
            setLoading(false);
        })
        .always(function() {
            //Maybe some code in the future
        });
}

window.onpopstate = function(e){
    if(e.state){
        navigate(e.state.path, true, e.state.sx, e.state.sy, e.state.cd);
    }
};

function setLoading(l){
    if(l){
        if(!main_div.classList.contains('fade'))main_div.classList.add('fade');
        if(!main_header.classList.contains('fade'))main_header.classList.add('fade');
        if(loading.classList.contains('fade'))loading.classList.remove('fade');
        loading.style.visibility = "visible";
        setTimeout(function() {
            if(main_div.classList.contains('fade'))main_div.style.visibility = "hidden";
            if(main_header.classList.contains('fade'))main_header.style.visibility = "hidden";
        }, 175);
    }else{
        if(!loading.classList.contains('fade'))loading.classList.add('fade');
        if(main_div.classList.contains('fade'))main_div.classList.remove('fade');
        if(main_header.classList.contains('fade'))main_header.classList.remove('fade');
        main_div.style.visibility = "visible";
        main_header.style.visibility = "visible";
        setTimeout(function() {
            if(loading.classList.contains('fade'))loading.style.visibility = "hidden";
        }, 175);
    }
}

function setTitle(title_str, subtitle_str){
    title.innerHTML = title_str+" <small>"+subtitle_str+"</small>";
}

function setPageTitlePrefix(prefix){
    page_title_prefix = prefix;
}

function setPageTitleSuffix(suffix){
    page_title_suffix = suffix;
}

function applyPageTitle(){
    page_title = "JCroft Usermanagement";
    if(page_title_prefix) {
        page_title = page_title_prefix+" "+page_title;
    }
    if(page_title_suffix) {
        page_title = page_title + " - " + page_title_suffix;
    }
    document.title = page_title;
}


/**
 * Fetches visible data from the url provided
 * @param queryString
 */
function parseQuery(queryString) {
    if(queryString.includes('?'))queryString = queryString.split('?')[1];
    var query = {};
    var pairs = (queryString[0] === '?' ? queryString.substr(1) : queryString).split('&');
    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split('=');
        query[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1] || '');
    }
    return query;
}

/**
 * Updates url visible data and hidden (history) data and pushes it to the current history
 * @param queryObj
 * @param dataObject
 */
function updateComponentData(queryObj, dataObject) {
    var query = "";
    var i = 0;
    for (var key in queryObj) {
        if (queryObj.hasOwnProperty(key)) {
            if(i===0)query+="?";
            else query+="&";
            query+=key;
            query+="=";
            query+=queryObj[key];
            i++
        }
    }
    curr_location = curr_location.split('?')[0]+query;
    window.history.replaceState({"path":curr_location, "sx":window.scrollX, "sy":window.scrollY, "cd":dataObject},"JCroft Usermanagement", "/p"+curr_location);
}

/**
 * Update page breadcrumb [{name: "Hey", icon: "fa-home", link: "/hello"},...]
 * @param breadcrumbData
 */
function updateBreadcrumb(breadcrumbData){
    var build = "";

    var i;
    for (i = 0; i < breadcrumbData.length; i++) {
        if(i===breadcrumbData.length-1)build+="<li class=\"active\">";
        else build+="<li>";
        if(breadcrumbData[i].link){
            build += "<a href=\"javascript:void(0);\" onclick=\"navigate('" + breadcrumbData[i].link + "')\">";
        }
        if(breadcrumbData[i].icon){
            build += "<i class=\"fa " + breadcrumbData[i].icon + "\"></i> ";
        }
        build += breadcrumbData[i].name;
        if(breadcrumbData[i].link){
            build += "</a>";
        }
        build+="</li>"
    }

    breadcrumb.innerHTML = build;
}

/**
 * Creates a modal
 * @param type danger/default/primary/info/warning/success
 * @param title
 * @param content HTML code
 * @param options [{name:"somename", action:"someaction", class:"pull-left/pull-right"},...]
 */
function showModal(type, title, content, options){
    modal.classList.remove("modal-danger");
    modal.classList.remove("modal-default");
    modal.classList.remove("modal-primary");
    modal.classList.remove("modal-info");
    modal.classList.remove("modal-warning");
    modal.classList.remove("modal-success");
    modal.classList.add("modal-"+type);
    modal_title.innerHTML = title;
    modal_body.innerHTML = content;

    var build = "";

    var i;
    for (i = 0; i < options.length; i++) {
            build += "<button type=\"button\" class=\"btn btn-outline";
            if(options[i].class){
                build += " "+options[i].class;
            }
            build += "\" onclick=\"";
            build += options[i].action;
            build += "\">";
            build += options[i].name;
            build += "</button>";
    }

    modal_footer.innerHTML = build;

    $('#modal').modal('show');
}

function hideModal(){
    $('#modal').modal('hide');
}