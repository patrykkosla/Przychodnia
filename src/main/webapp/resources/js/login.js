/* 
 * Mój jest kawałek podłogi...  * 
 */

function handleLoginRequest(xhr, status, args) {
    if (args.loggedIn && args.role === "nurse") {
        window.location.replace("/przychodnia/staff/przychodnia.xhtml");
    }else if (args.loggedIn && args.role === "doc") {
        window.location.replace("/przychodnia/staff/doctor.xhtml");
    } else{
         window.location.replace("/przychodnia/index.xhtml");
    }

}
