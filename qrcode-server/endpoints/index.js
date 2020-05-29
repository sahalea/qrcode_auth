import { Router } from "express";
const router = Router();

import registerUser from "./registerUser";
import loginUser from "./loginUser";

router.post("/register-user", registerUser);
router.post("/login-user", loginUser);

export default {
  path: "/api",
  handler: router,
};
