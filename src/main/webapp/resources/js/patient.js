/* 
 * Mój jest kawałek podłogi...  * 
 */
function handleAppoitmentPageRequest(xhr, status, args) {
    if ( !args.docSet) {
        
    }
    else {
        
        // similar behavior as an HTTP redirect
        window.location.replace("/przychodnia/patient/bookingappoitment.xhtml");
    }
}

function handleAppoitmentRequest(xhr, status, args) {
    if ( !args.bookSet) {
        // oncomplete="PF('doctorDialog').hide()"
        // PF('doctorDialog').jq.effect("shake", {times:5}, 100);
        console.log(args.test);
    }
    else {
       // PF('doctorDialog').hide();
        // similar behavior as an HTTP redirect
        window.location.replace("/przychodnia/patient/bookingappoitment.xhtml");
    }
}



