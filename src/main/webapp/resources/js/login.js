/* 
 * Mój jest kawałek podłogi...  * 
 */

function handleLoginRequest(xhr, status, args) {
    if (args.loggedIn && args.role === "NURSE") {
        window.location.replace("/przychodnia/staff/przychodnia.xhtml");
    }else if (args.loggedIn && args.role === "DOCTOR") {
        window.location.replace("/przychodnia/staff/doctor.xhtml");
    }else if (args.loggedIn && args.role === "ADMIN") {
        window.location.replace("/przychodnia/admin/admin.xhtml");
    }else{
         window.location.replace("/przychodnia/index.xhtml");
    }

}
