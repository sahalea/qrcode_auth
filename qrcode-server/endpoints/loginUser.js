import schema from "../data/schema";
import crypto from "crypto";
import { cryptConfig } from "../serverconfig";

const loginUser = async (req, res) => {
  //const model = schema["user"];
  const data = req.body;

  const a = decreptVal(data.userKey);
  console.log(a);
  // if (model) {
  //   let payload = await model.findOne({
  //     where: {
  //       userKey: data.userKey,
  //     },
  //   });
  //   if (payload !== null) {
  //     payload = {
  //       name: payload.name,
  //       username: payload.username,
  //     };
  //     return res.status(200).json({ success: true, data: payload });
  //   } else {
  //     return res
  //       .status(200)
  //       .json({ success: false, data: "No registered user on this key" });
  //   }
  // }
};
const decreptVal = (key) => {
  let decipher = crypto.createDecipher(
    cryptConfig.algorithm,
    cryptConfig.password
  );
  let dec = decipher.update(key, "hex", "utf8");
  dec += decipher.final("utf8");
  return dec;
};

export default loginUser;
