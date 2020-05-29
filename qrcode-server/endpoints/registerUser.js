import schema from "../data/schema";
import crypto from "crypto";

const registerUser = async (req, res) => {
  const model = schema["user"];
  let data = req.body;
  try {
    if (await validateEmail(model, data.username)) {
      const userKey = `${data.username}${data.password}`;
      data.userKey = setEncrypt(userKey);
      data.password = setEncrypt(data.password);
      let entity = await model.create(data);
      entity = {
        userKey: entity.userKey,
      };
      return res.status(200).json({ success: true, data: entity });
    } else {
      return res
        .status(200)
        .json({ success: false, data: "Username Alrey Exists" });
    }
  } catch (error) {
    return res.status(200).json({ success: false, data: error });
  }
};

const setEncrypt = (key) => {
  return crypto.createHash("sha1").update(key).digest("hex");
};

const validateEmail = async (model, username) => {
  const effectedRow = await model.findOne({
    where: {
      username: username,
    },
  });
  if (effectedRow) return false;
  else return true;
};

export default registerUser;
