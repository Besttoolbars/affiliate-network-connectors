package net.besttoolbars.dcm

import net.besttoolbars.dcm.dto.DCMError
import java.lang.Exception

class DCMException(
    val method: String,
    val errors: List<DCMError> = emptyList()
): Exception("DCM api exception in method $method, errors: $errors")