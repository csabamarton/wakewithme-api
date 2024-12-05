package com.wakewithme.api.user.web.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UserApiDocumentation {

    @Operation(summary = "Retrieve user details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @interface GetUserById {
    }

    @Operation(summary = "Update user profile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User profile updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to update user profile")
    })
    @interface UpdateUser {
    }

    @Operation(summary = "Delete user account")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User account deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to delete user account"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @interface DeleteUser {
    }
}
